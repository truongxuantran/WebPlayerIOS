# MinischoolView
Tutorial for add Minischool webview to react native app.

### Android

#### Add Minischool Webview
- First copy 4 files **MinischoolManager.java, MinischoolPackage.java, MinischoolView.java, MinischoolViewModule.java** from android/app/src/main/java/com/msdemorn into your project.
- Add this code in your project to register MinischoolPackage()
```
@Override
    protected List<ReactPackage> getPackages() {
      @SuppressWarnings("UnnecessaryLocalVariable")
      List<ReactPackage> packages = new PackageList(this).getPackages();
      packages.add(new MinischoolPackage());
      return packages;
    }
```
- Add **MinischoolView.js** into your react native code to interactive with android webview module. 

#### Add react native code for get current url from intent.
- Using this code for get url from intent url scheme.
```
async onChangeStudentUrl() {
    let self = this
    let url_scheme = ''
    let url = ''

    if (Platform.OS === 'android') {
      url_scheme = await Linking.getInitialURL()


      url = await this.replaceProtocol(url_scheme)
      if (url) {
        self.setState({student_url: url})
      }
    } else {
      await Linking.addEventListener('url', async (url_scheme) => {
        url = await self.replaceProtocol(url_scheme)

        if (url) {
          self.setState({student_url: url})
        }
      })
    }
  }
```
