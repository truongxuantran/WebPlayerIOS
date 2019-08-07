/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Fragment} from 'react';
import {
  SafeAreaView,
  StyleSheet,
  ScrollView,
  Button,
  View,
  Text,
  StatusBar,
  TouchableOpacity,
  requireNativeComponent,
  ToastAndroid,
  Platform,
  Linking,
  AppState
} from 'react-native'
import Url from 'url-parse'

const MinischoolView = require('./MinischoolView')

export default class App extends React.Component{

  constructor(props) {
    super(props)

    this.state = {
      student_url: 'https://google.com'
    }
  }

  async componentDidMount() {
    let self = this

    // Bind event for detect AppState change from background to active.
    AppState.addEventListener('change', await self._handleAppStateChange.bind(this));

    // Attach event for update student url when url linking change.
    Linking.addEventListener('url', async (obj_url)=>{ 
      let url = await this.replaceProtocol(obj_url.url)
      if (url) {
        self.setState({student_url: url})
      }
    });

    // Run check custom url scheme first.
    await self.onChangeStudentUrl()
  }

  async componentWillUnmount() {
    let self = this

    AppState.removeEventListener('change', await self._handleAppStateChange.bind(this));
  }

  async _handleAppStateChange(current_app_state) {
    let self = this

    if (current_app_state === 'active') {
      // Re-run check custom url scheme again.
      await self.onChangeStudentUrl()
    }
  }

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

  /**
   * Process replace msp3:// to https:// for url scheme.
   */
  replaceProtocol = (url_scheme) => {
    if (! url_scheme) {
      return null
    }

    let url = Url(url_scheme)
    let protocol_header = 'https'
    url.set('protocol', protocol_header)
    
    return url.href
  }

  onStarted = e => {
    ToastAndroid.show('on started: ' + e.pathName, ToastAndroid.SHORT)
  }

  onEnded = e => {
    ToastAndroid.show('on ended: ' + e.pathName, ToastAndroid.SHORT)
  }

  render() {
    return (
      <View style={styles.container}>
        <MinischoolView style={ styles.wrapper }
          url={this.state.student_url}
          onStarted={this.onStarted}
          onEnded={this.onEnded}
        />

      </View>
    );
  }
}
const styles = StyleSheet.create({
  container: {
    flex: 1, alignItems: "stretch"
  },
  wrapper: {
    flex: 1, alignItems: "center", justifyContent: "center"
  },
  border: {
    borderColor: "#eee", borderBottomWidth: 1
  },
  button: {
    fontSize: 50, color: "orange"
  }
});