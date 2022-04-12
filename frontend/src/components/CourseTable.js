import axios from "axios";
import { Component } from "react";
import Table from 'react-bootstrap/Table'
import CourseTableElement from "./CourseTableElement";




class CourseTable extends Component {
    state = {
        elements: [],
        refs: [] //references of CourseTableElements to update enrolled students lists
    }

    componentWillMount() {
        this.props.getRef(this)
        this.retrieveData()
    }

    createTableElementComponents = (elements) => {
        return elements.map( (element, index) => {
            return <CourseTableElement updateParent={() => {this.retrieveData()}} id={element.id} title={element.title}
                level={element.level} index={index} getRef={  (ref) => {
                    this.addRef(ref, index)
                }
                } />
        } )
    }

    retrieveData() {
        axios.get("http://localhost:8082/course").then( (result) => {
            let tableElements = this.createTableElementComponents(result.data)
            this.state.refs.forEach(element => {
                element.getEnrolledStudents()
            });
            this.setState({elements: tableElements})
        } )
        
    }


    addRef = (ref, index) => {
        let newRefs = [...this.state.refs]
        newRefs[index] = ref
        this.setState({refs: newRefs})
    }
    

    render() {

        return (
            <div>
                <Table striped bordered hover size="sm">
                <thead>
                        <tr>
                            <th>Course ID</th>
                            <th>Title</th>
                            <th>Level</th>
                            <th>Enrolled Students</th>
                            <th>Delete</th>
                        </tr>
                </thead>
                <tbody>
                    {this.state.elements}
                </tbody>
                </Table>
            </div>
        )
    }

}

export default CourseTable