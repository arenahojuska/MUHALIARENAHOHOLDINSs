*** Settings ***
Library    OperatingSystem
Library    Process
Library    Collections
Library    BuiltIn

*** Variables ***
${LOCUST_FILE}    C:/Users/arenaho.muhali/PycharmProjects/MUHALI_ARENAHO_HOLDINGS/API TESTING/performance_testing.py
${RESULT_CSV}     locust_result_stats.csv
${USERS}          500
${SPAWN_RATE}     80
${RUN_TIME}       30s
${HOST}           https://pokeapi.co

*** Keywords ***
Performance Test
    ${result}=    Run Process
    ...    locust
    ...    -f    ${LOCUST_FILE}
    ...    --headless
    ...    -u    ${USERS}
    ...    -r    ${SPAWN_RATE}
    ...    --host    ${HOST}
    ...    --run-time    ${RUN_TIME}
    ...    --csv    locust_result
    ...    shell=True
    ...    stdout=TRUE
    ...    stderr=TRUE
    ...    timeout=40s



    Should Be Equal As Integers    ${result.rc}    0    Process should finish successfully
    Should Exist    ${RESULT_CSV}
