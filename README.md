# xUtils
我的工具类整理

步骤1.将其添加到根build.gradle中：

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
步骤2.添加依赖项

	dependencies {
	        implementation 'com.github.seul8660:xUtils:v1.0'
	}
  
步骤3. 在application中初始化 

xBase.init(this);
