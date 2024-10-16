# Тестовое задание

Тестовое задание тестирует API постов

## Структура
### src/main
* Пакет `client` отвечает за отправку запросов на сервер
* В пакете `model` лежат ДТО
* В пакете `service` лежат вспомогательные методы для тестов
* В пакете `util` - вспомогательные методы для отправки запросов

### src/test
* В пакете `negative` лежат негативные тесты
* В пакете `positive` лежат позитивные тесты
* В пакете `util.builder` лежат классы для создания тестовых ДТО
* В классе `Task2` лежит второе тестовое задание

## Как запустить
Запустить скрипт `runTest.sh`, либо выполнить слелующие команды:
```bash
# Windows:
./gradlew.bat clean
./gradlew.bat allureReport --depends-on-tests
./gradlew.bat allureServe

# Linux/MacOS:
./gradlew clean
./gradlew allureReport --depends-on-tests
./gradlew allureServe
```
