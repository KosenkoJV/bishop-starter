Для всех задач начало одно:
 	Запустить class Application
 	В терминале IDE ввести:


1 Отправка команды

curl -Uri http://localhost:8080/commands -Method POST -Body '{
  "description": "Проверить энергоблок",
  "priority": "COMMON",
  "author": "Рипли",
  "time": "2025-07-20T12:00:00Z"
}' -ContentType "application/json"
 	//Увидим что сервер принял команду, в логах Application появится: Аудит: вызывается метод submit


2 Проверка статуса

curl http://localhost:8080/status
//completedByAuthor - Сколько выполненых задач, queuedCommands - Сколько задач в очереди

3 Приоритет

curl -Uri http://localhost:8080/commands -Method POST -Body '{
  "description": "Проверить энергоблок",
  "priority": "CRITICAL",
  "author": "Рипли",
  "time": "2025-07-20T12:00:00Z"
}' -ContentType "application/json"
//В логах увидим что это команда будет выполнена немедленно из-за приоритета CRITICAL

4 Проверка на ошибки

curl -Uri http://localhost:8080/commands -Method POST -Body '{
  "description": "",
  "priority": "COMMON",
  "author": "Рипли",
  "time": "2025-07-20T12:00:00Z"
}' -ContentType "application/json"
//выдаст ошибку так как описание пустое