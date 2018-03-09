<h2>ZoomInTextView</h2>

[![](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![](https://img.shields.io/badge/Download-1.0.1-blue.svg)](https://bintray.com/medathsieh/AndroidLib/ZoomInTextView/1.0.1)

Click on textview and automatic zoom in

<img src="https://github.com/Samuel-Hsieh/ZoomInTextView/blob/master/image/prototype.gif" width="350"/>

<h2>How do I use it?</h2>

<h3>Setup</h3>

On the bulid.gradle

```gradle
repositories {
    jcenter()
}
```

```gradle
dependencies {
  compile 'com.samuelhsieh:ZoomInTextView:1.0.1'
}
```
<h3>Functions</h3>

<h4>Dynamically add components</h4>

```java
ZoomInTextView ztv = new ZoomInTextView.Builder(this)
                .Text(R.string.hello)
                .TextColorRes(android.R.color.holo_orange_dark)
                .TextSize(R.dimen.MyTextSize)
                .TextMaxSize(30f) //30sp
                .Duration(1000) //1s
                .build();
```

<h4>In Java code</h4>

```java
ZoomInTextView ztv = findViewById(R.id.zoomIn_tv);
        ztv.setText(R.string.app_name);
        ztv.setTextSize(20f); //20sp
        ztv.setTextMaxSize(R.dimen.MyTextMaxSize);
        ztv.setTextColorRes(R.color.colorAccent, null);
        ztv.setDuration(500);
```

<h4>In XML</h4>

```xml
<com.deerlight.zoomintextview.ZoomInTextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/zoomIn_tv"
        android:layout_centerInParent="true"
        android:text="@string/app_name"
        android:textColor="@color/colorPrimary"
        android:textSize="18sp"
        app:duration="500"
        app:textMaxSize="30sp" />
```

<h2>License</h2>

	Copyright 2018 Samuel Hsieh

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
