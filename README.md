
# Летняя практика Java 2020  
## Содержание  
- [Летняя практика Java 2020](#летняя-практика-java-2020)  
  - [Спецификация](#спецификация)  
  - [Роли](#роли)   
  - [Правила работы с репозиторием](#правила-работы-с-репозиторием)  
  - [Этапы](#этапы)  
## Спецификация  
Данное решение позволяет визуализировать алгоритм поиска максимального паросочетания в двудольном графе. Доли графа представляют собой группу пользователей социальной сети ВКонтакте и группу сообществ, на которые они подписаны. Группа пользователей состоит из целевого пользователя и 5-15 его друзей. Группа сообществ &ndash; это множество состоящее из 5 первых сообществ каждого пользователя первой группы.  
  
## Роли  
* [Нечепуренко Никита](https://github.com/nechepurenkoN): лидер, алгоритмист.  
* [Терехов Александр](https://github.com/snchz29): фронтенд, тестирование.  
* [Торосян Тимофей](https://github.com/sandman595): tbd.  
  
## Правила работы с репозиторием  
+ В ветке zero_task находятся именные папки с заданиями для допуска.
+ В ветке master расположена стабильная версия проекта.
+ Ветка slave предназначена для тестовых версий проекта и для создания из нее промежуточных веток.
+ Ветки унаследованные от slave имеют названия состоящие из четырех первых букв фамилии разработчика.
+ В проекте используется доска для мониторинга состояний текущих задач.
+ После выполнения промежуточной задачи разработчик пушит локальную ветку в свою ветку на GitHub и делает PR в slave.

## Этапы  
1. Разработка спецификации, распределение ролей, подготовка репозитория. До 03.07.2020
2. Реализация структур данных для хранения графа, данных пользователя и групп; разработка базовой разметки пользовательского интерфейса. До 06.07.2020
3. Отладка и тестирование алгоритма поиска максимального паросочетания. До 06.07.2020
4. Визуализация алгоритма на простейших тестовых данных. До 08.07.2020
5. Разработка парсера открытого API социальной сети ВКонтакте, интеграция реальных данных в готовое решение. До 10.07.2020
6. Отладка программы, рефакторинг кода, написание комментариев. До 12.07.2020

## Версии программы
1. Прототип вклаючает в себя реализацию этапов 1-3.
2. Первая версия включает в себя реализацию этапа 4.
3. Вторая версия включает в себя реализацию этапа 5.
4. Финальная версия включает в себя реализацию этапа 6.
