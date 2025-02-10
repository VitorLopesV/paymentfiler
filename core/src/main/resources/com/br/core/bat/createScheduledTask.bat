@echo off
set taskname=PaymentFiler_Organizer
set jarfilepath=C:\Users\vitor\IdeaProjects\paymentfiler\organizer\target\organizer-0.0.1-SNAPSHOT.jar

REM Verifique se já existe uma tarefa com o mesmo nome e a exclua
schtasks /query /tn %taskname% >nul 2>&1
if %ERRORLEVEL%==0 (
    echo Removendo tarefa existente %taskname%
    schtasks /delete /tn %taskname% /f
)

REM Cria uma nova tarefa agendada para rodar a cada hora
echo Criando nova tarefa agendada %taskname%
schtasks /create /tn %taskname% /tr "java -Xmx256m -jar \"%jarfilepath%\"" /sc hourly /mo 1 /ru "SYSTEM" /f

echo Tarefa agendada criada com sucesso para rodar a cada hora com limite de memória definido
pause