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
} from 'react-native';
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
    let url_scheme = ''

    if (Platform.OS === 'android') {
      url_scheme = await Linking.getInitialURL()

      let url = await this.replaceProtocol(url_scheme)
      if (url) {
        self.setState({student_url: url})
      }
    } else {
      await Linking.addEventListener('url', async (url_scheme) => {
        let url = await self.replaceProtocol(url_scheme)

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