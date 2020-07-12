
# Летняя практика Java 2020. VK Bipartite  
## Содержание  
- [Установка программы](#установка-программы)
- [Получение необходимых данных](#получение-необходимых-данных)
- [Использование программы](#использование-программы)
- [Авторы](#авторы)

## Установка программы  
Скачайте .jar файл из папки [spbetu2020_summer_practice_jar](https://github.com/nechepurenkoN/spbetu2020_summer_practice/spbetu2020_summer_practice_jar) или сделайте clone этого репозитория.
Перед запуском необходимо добавить в .jar файл или корневую директорию файл app.config следующего содержания:
```
appId
secureKey
serviceToken
accessToken
```

## Получение необходимых данных
Для приложения понадобятся сервисный ключ и пользовательский. Это необходимо для увеличения числа запросов к API  в секунду.
Создайте [новое приложение](https://vk.com/apps?act=manage), и перейдите в окно настроек.
Вам понадобятся зачёркнутые поля (см. ниже):

![Данные о приложении](https://i.imgur.com/I53st6S.png)

Это первые 3 строки Вашего app.config файла.

Затем скопируйте в адресную строку следующую ссылку:
https://oauth.vk.com/authorize?client_id= + id приложения + &display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,groups,photos&response_type=token&v=5.120
После отправки в адресной строке появится access_token. Это четвертая строчка app.config.

## Использование программы
После запуска программы Вы увидите окно:

![Приветственное окно](https://i.imgur.com/gBI43Kk.png)

В текстовое поле введите id пользователя и нажмите ```Start!``` или кнопку ```Enter```

В появившемся окне управляйте выполнением алгоритма с помощью кнопок со стрелками в нижней части.

![Выполнение алгоритма](https://i.imgur.com/ftyLZ0r.png)

## Авторы
[@nechepurenkon](https://github.com/nechepurenkoN)
[@snchz29](https://github.com/snchz29)
[@sandman595](https://github.com/sandman595)
Дополнительную информацию см. в вики.
