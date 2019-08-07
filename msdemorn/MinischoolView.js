
import React from 'react'
import {
    requireNativeComponent,
    View,
    PermissionsAndroid
} from 'react-native'

class MinischoolView extends React.Component {

    constructor() {
        super()
        this.onChange = this.onChange.bind(this)
    }

    async componentDidMount() {
        await this.requestVideoPermission()
        await this.requestAudioPermission()
    }

    async requestVideoPermission() {
        try {
            const granted = await PermissionsAndroid.request(PermissionsAndroid.PERMISSIONS.CAMERA, {
                'title': 'Request Camera',
                'message' : 'Please allow permission to access camera.'
            })

            if (granted === PermissionsAndroid.RESULTS.GRANTED) {
                console.log("You can use the camera")
              } else {
                console.log("camera permission denied")
              }
        } catch(err) {
            console.warn(err)
        }
    }

    async requestAudioPermission() {
        try {
            const granted = await PermissionsAndroid.request(PermissionsAndroid.PERMISSIONS.RECORD_AUDIO, {
                'title': 'Request Audio',
                'message' : 'Please allow permission to access aiduo.'
            })

            if (granted === PermissionsAndroid.RESULTS.GRANTED) {
                console.log("You can use the audio")
              } else {
                console.log("audio permission denied")
              }
        } catch(err) {
            console.warn(err)
        }
    }

    async requestModifyAudioPermission() {
        try {
            const granted = await PermissionsAndroid.request(PermissionsAndroid.PERMISSIONS.MODIFY_AUDIO_SETTINGS, {
                'title': 'Request Modify Audio',
                'message' : 'Please allow permission to modify audio.'
            })

            if (granted === PermissionsAndroid.RESULTS.GRANTED) {
                console.log("You can use the location")
              } else {
                console.log("location permission denied")
              }
        } catch(err) {
            console.warn(err)
        }
    }

    onChange(event) {
        switch(event.nativeEvent.pathName) {
            case 'onStarted':
                if (! this.props.onStarted) {
                    return
                }
                this.props.onStarted({
                    pathName: event.nativeEvent.pathName,
                })
                break
            case 'onEnded':
                    if (! this.props.onEnded) {
                        return
                    }
                this.props.onEnded({
                    pathName: event.nativeEvent.pathName
                })
                break
        }
    }

    render() {
        return (
            <Minischool {...this.props} onChange={this.onChange} />
        )
    }
}

MinischoolView.propTypes = {
    ...View.propTypes,
}

const Minischool = requireNativeComponent('MinischoolView', MinischoolView, {
    nativeOnly: { onChange: true }
})

module.exports = MinischoolView