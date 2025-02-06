@echo off
set taskname=PaymentFiler_Organizer

REM Verifique se a tarefa existe
schtasks /query /tn %taskname% >nul 2>&1
if %ERRORLEVEL%==0 (
    echo Removendo tarefa agendada %taskname%
    schtasks /delete /tn %taskname% /f
    echo Tarefa %taskname% removida com sucesso.
) else (
    echo Tarefa %taskname% não foi encontrada.
)

pause