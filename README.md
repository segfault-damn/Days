# Days

## Record daily life and make todo list


This application designs to **record users' daily life** and help users **make todo list** in the calender.
Users can *write their diary* and *select their mood on that day*. The application allows user to *see the 
statistic of their mood in each month*. In addition, todo list allows user to *add or remove events 
and habits* (which can be marked as done). Habits also has a statistic overview of each month. Some days 
can also be marked as *Anniversary* and a *countdown* will be shown to remind users.

## Five main function:
- keep **Diary**
- set **Events**
- record **Mood**
- track **Habit**
- remember **Anniversary**

## User stories
*Diary*
- As a user, I want to be able to add diaries to one day.
- As a user, I want to be able to delete or edit diary in one day.
- As a user, I want to be able to add tag or modify tag to a diary.
- As a user, I want to view diaries by tag or date.

*Events*
- As a user, I want to be able to add todo event to the list of events.
- As a user, I want to be able to delete todo event or change time in the list of events.
- As a user, I want to be able to view todo events in one day.

*Mood*
- As a user, I want to be able to select my mood every day.
- As a user, I want to be able to view my mood in any day.
- As a user, I want to view the statistic data of my mood in each month.

*Habit*
- As a user, I want to add habits to a habit list.
- As a user, I want to remove habits from the habit list.
- As a user, I want to select some habits from that list and mark it as done.
- As a user, I want to view the statistic data of completed habit in each month.

*Anniversary*
- As a user, I want to set anniversary in a particular day.
- As a user, I want to be able to remove anniversary in one selected day.
- As a user, I want to view a selected anniversary.

## Instruction for Grader
The main panel includes five buttons leading to five panels. In each panel (except for the main panel), 
the "back" button on the right side enables you to return to the main menu. 

- You can generate the first required event by clicking "event" button. In the event
panel, you can add or remove events to the event list clicking button called "add" and
"remove". Add event need to input the time of that event. Click "Confirm" and click "view" 
to see the event list in one particular date.

- You can generate the second required event by clicking "habit" button. In the habit
panel, you can click the menu bar called "list" and select "add" or "remove" (two menu items),
and click "Confirm". Then if you click "add" or "remove" again, you can see the current habit
list. (Another feature, click "mark" button to mark one habit as done in one particular date. Click
"view" to see the status of each habit in your selected date!)

- You can locate my visual component by clicking "Mood" button. Click set to record your mood today.
Then click "view" to see your mood today. Enter today's date (today's date is default input in that panel)
and click "Confirm". You should see an image which is related to your mood today in "view"
panel. You are able to change your mood today by clicking "set" button and choose again.
Click "view" and enter today's date to see different images corresponding to different moods.

- You can save the state of my application by clicking "save" button on the right side of the main menu.

- You can reload the state of my application by clicking "load" button on the right side of the main menu.