@echo off
set taskname=PaymentFiler_Organizer
set batfilepath=C:\Users\vitor\IdeaProjects\paymentfiler\core\target\bat\executeOrganizer.bat

REM Verifique se já existe uma tarefa com o mesmo nome e a exclua
schtasks /query /tn %taskname% >nul 2>&1
if %ERRORLEVEL%==0 (
    echo Removendo tarefa existente %taskname%
    schtasks /delete /tn %taskname% /f
)

REM Criar uma nova tarefa agendada para rodar a cada 5 minutos
echo Criando nova tarefa agendada %taskname%
schtasks /create /tn %taskname% /tr %batfilepath% /sc minute /mo 5 /ru "SYSTEM" /f

echo Tarefa agendada criada com sucesso para rodar a cada 5 minutos
pause