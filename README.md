Jongo Play 2.0 Spikes
=====================================

This project aims to test how jongo can be used with Play 2
It has been created subsequent these 2 questions : 

https://github.com/bguerout/jongo/issues/34

https://groups.google.com/forum/?hl=fr&fromgroups#!topic/jongo-user/ydYeEaZuU2M

First spike : Share a Mongo and Jongo instance across an application :
-----------------------------------------------------------------------
A JongoPlugin has been created and declared into 'conf/play.plugins' file.

This plugin can be used accros the application using the following snippet : 

```java
 JongoPlugin plugin = JongoPlugin.getJongoPlugin();
 Jongo jongo = plugin.getJongo("myDatabase");
```

Second spike : Create a Jongo Model
-----------------------------------------------------------------------
In progress...
Should look like
```java
public class User extends JongoModel {
...
```