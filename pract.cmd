echo off
cls
git init
git status
git add .
git status

git commit -m "1.0"
git branch -M main
git remote add origin https://github.com/ingenieroadriancosta/ProyectActions.git
git push -u origin main


pause


