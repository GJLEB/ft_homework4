Тесты можно проводить в двух браузерах chrome и firefox в chrome можно проводить тесты в режиме  invisible.
Запуск тестов на браузере на примере firefox с командной строки mvn test  -Dtest=Test1 -Dbrowser=firefox verify, если не указказан -Dbrowser
mvn test  -Dtest=Test1 verify тесты будут запущены в браузере chrome.
