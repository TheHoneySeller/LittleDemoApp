import axios from "axios";
import { Component } from "react";

import Button from "react-bootstrap/Button";

class CourseTableElement extends Component {
    state = {
        enrolledStudents: []
    }

    componentWillMount() {
        this.getEnrolledStudents()
        this.props.getRef(this)
    }

    deleteCourse = (courseId) => {
        axios.delete("http://localhost:8082/course?courseId="+ courseId).then( (response) => {
            this.props.updateParent()
        })
    }

    getEnrolledStudents(){
        axios.get("http://localhost:8080/enrolledInCourse?courseId="+this.props.id).then( (result) => {
            this.setState({enrolledStudents: result.data})
        } )
    }

    render() {
        return (<tr key={this.props.index}>
                <td>
                    {this.props.id}
                </td>
                
                <td>
                    {this.props.title}
                </td>

                <td>
                    {this.props.level}
                </td>

                <td>
                    {this.state.enrolledStudents.map( (student, index) => {
                        return (<div>{"(id=" + student.id.toString() + ") " + 
                        student.name + " " +
                        student.surname}<br/></div> )
                        } )}
                </td>

                <td>
                    <Button variant="danger" onClick={(event) => {
                        event.preventDefault()
                        this.deleteCourse(this.props.id)
                    }}>
                        Delete
                    </Button>
                </td>
            </tr>)
    }
}

export default CourseTableElement