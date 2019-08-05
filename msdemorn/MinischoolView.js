
import React from 'react'
import {
    requireNativeComponent,
    View
} from 'react-native'

class MinischoolView extends React.Component {

    constructor() {
        super()
        this.onChange = this.onChange.bind(this)
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