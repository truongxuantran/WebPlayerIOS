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
  ToastAndroid
} from 'react-native';

import { NativeModules, NativeEventEmitter } from 'react-native'

const MinischoolView = requireNativeComponent("MinischoolView")
// const studentUrl = "https://dev-p3.ekidpro.com/student/Y2sxNTY0Mzk1NDY0OTA1b75e3d8d31734854a0be338901a68169"
const studentUrl = "http://103.82.197.22:17000/"

export default class App extends React.Component{

  onChangedStatus = e => {
    console.log("status: "+e.nativeEvent.status)
  }
  onErrorOccured = e => {
    console.log("error: "+e.nativeEvent.error)
  }
  _onChange = e => {
    ToastAndroid.show('A pikachu appeared nearby !' + e.nativeEvent, ToastAndroid.SHORT)
  }

  render() {
    return (
      <View style={styles.container}>

        <Button
          title='Open Browser'
        />

        <MinischoolView style={ styles.wrapper } 
          url={studentUrl}
          onChange={this._onChange}
          // onChangedStatus={this.onChangedStatus}
          // onErrorOccured={this.onErrorOccured}
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