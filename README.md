# Recyclerview-with-JsonArray-MVVM

A) This app fetches data using Coroutines and displays it in an recycler view.  MVVM clean architecture  
pattern is used. Jetpack library is used mainly Livedata, Viewmodel.

B) Scroll to top feature has been provided on the action bar. 

C) Both Unit(Coroutines) and UI Espresso tests cases(Data enter and error state) included.

D) Network connection check has been provided through a alert dialog.

E) A Progress bar is given with a delay.

F) Dynamic recycler view is maintained according to the data. 

Below are the snapshot of app with explanations:

**While network connection not available inside the app**
![Screenshot_1638097785](https://user-images.githubusercontent.com/34192499/143765541-f751ca10-0edb-4516-b569-e4d823d0ae21.png)

**When app is on restart state without connection**
![Screenshot_1638097812](https://user-images.githubusercontent.com/34192499/143765584-27a3cf21-9fea-4801-9dbd-2d7c81ce9415.png)

**When app connects with any form of network**
![Screenshot_1638097853](https://user-images.githubusercontent.com/34192499/143765593-dd13d6a3-7ba0-48a3-9278-5705637b52ed.png)

**Showcase of the app**
![Screenshot_1638132284](https://user-images.githubusercontent.com/34192499/143785318-08546bfd-4ee0-4e11-8b94-123fc244915c.png)


UI Test with passed and failed error state :

<img width="2034" alt="Screenshot 2021-11-28 at 4 14 23 PM" src="https://user-images.githubusercontent.com/34192499/143764914-065c219d-7e7e-43ba-b3cb-fda27745e405.png">

Croutine Test :

<img width="2042" alt="Screenshot 2021-11-28 at 4 22 00 PM" src="https://user-images.githubusercontent.com/34192499/143764929-b46759ac-01f0-439a-b9f9-9d9843dddfd7.png">

Desired history of commits: 

<img width="2048" alt="Screenshot 2021-11-28 at 5 01 37 PM" src="https://user-images.githubusercontent.com/34192499/143765981-622b4548-7f21-4454-abac-fa030fd3bac0.png">



