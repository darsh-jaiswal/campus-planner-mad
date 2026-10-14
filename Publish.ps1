$ErrorActionPreference = "Stop"
Set-Location -LiteralPath $PSScriptRoot

function Invoke-Checked {
    param([string]$Program, [string[]]$Arguments)
    & $Program @Arguments
    if ($LASTEXITCODE -ne 0) { throw "$Program failed with exit code $LASTEXITCODE. Fix the error above, then rerun this script." }
}

Invoke-Checked -Program "gh" -Arguments @("auth", "status")
$owner = (& gh api user --jq .login).Trim()
if ($LASTEXITCODE -ne 0 -or !$owner) { throw "GitHub account verification failed. Run gh auth login, then rerun." }
Invoke-Checked -Program "vercel" -Arguments @("whoami")

Invoke-Checked -Program "git" -Arguments @("add", ".")
& git diff --cached --quiet
if ($LASTEXITCODE -eq 1) {
    Invoke-Checked -Program "git" -Arguments @("commit", "-m", "Prepare Campus Planner website for Vercel deployment")
} elseif ($LASTEXITCODE -ne 0) { throw "Could not check staged changes." }

$repository = "$owner/campus-planner-mad"
$savedPreference = $ErrorActionPreference
try {
    $ErrorActionPreference = "Continue"
    $origin = & git remote get-url origin 2>$null
    $originExit = $LASTEXITCODE
} finally { $ErrorActionPreference = $savedPreference }
if ($originExit -eq 0) {
    if ($origin -notmatch ([regex]::Escape($repository))) { throw "Origin points to another repository: $origin" }
    Invoke-Checked -Program "git" -Arguments @("push", "-u", "origin", "main")
} else {
    try {
        $ErrorActionPreference = "Continue"
        & gh repo view $repository --json name 2>$null | Out-Null
        $repositoryExit = $LASTEXITCODE
    } finally { $ErrorActionPreference = $savedPreference }
    if ($repositoryExit -eq 0) {
        throw "Repository $repository already exists. Verify its ownership and contents before connecting this project."
    }
    Invoke-Checked -Program "gh" -Arguments @("repo", "create", $repository, "--public", "--source=.", "--remote=origin", "--push", "--description", "Campus Planner Java Android MAD assignment with Vercel website and APK download")
}

Invoke-Checked -Program "vercel" -Arguments @("link", "--yes", "--project", "campus-planner-mad")
Invoke-Checked -Program "vercel" -Arguments @("project", "inspect", "campus-planner-mad")
Invoke-Checked -Program "vercel" -Arguments @("deploy", "--prod", "--yes")
Write-Host "GitHub repository: https://github.com/$repository"
Write-Host "Vercel deployment URL is printed above."
