# jelli-tac-module
A Terms and Conditions module that can be added quickly to android projects. Built to go with other jelli-projects

# Usage:

Add the library to your android project and declare a layout as follows:

```xml
<?xml version="1.0" encoding="utf-8"?>
<com.jelliroo.api.tac.TACRelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.jelliroo.api.tacapp.MainActivity">

</com.jelliroo.api.tac.TACRelativeLayout>
```

Set this layout as content view in your activity and load TACRelativeLayout.

```java
TACRelativeLayout tac = (TACRelativeLayout) findViewById(R.id.activity_main);
```

Init the view by passing arguments

```java
tac.initViews(
//context
this,
//eg. Welcome to App
R.string.heading,   
//eg. Click on 'Agree' to accept the
R.string.small_message,   
//eg. App Terms and Conditions
R.string.tac_name,
//eg. Agree and Continue
R.string.agree_button,
//logo drawable resource
R.drawable.ic_all_out_black_24dp);
```

Catch when user taps on 'Agree' button

```java
tac.setOnAgreeListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "Agreed", Toast.LENGTH_LONG).show();
    }
});
```

Catch when user taps on 'App Terms and Conditions'

```java
tac.setOnTacClickedListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "go to url", Toast.LENGTH_LONG).show();
    }
});
```

Supported screens configurations:

* layout
* layout-land
* layout-large
* layout-large-land
* layout-xlarge
* layout-xlarge-land

Screenshots

| Layout | Screenshot |
|--------|------------|
|layout|<img src="https://raw.github.com/jelliroo/jelli-tac-module/master/shots/layout.png" width="250">|
|layout land|<img src="https://raw.github.com/jelliroo/jelli-tac-module/master/shots/layout-land.png" height="250">|
