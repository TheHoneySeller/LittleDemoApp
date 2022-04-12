import axios from "axios";
import { Component } from "react";
import Button from "react-bootstrap/Button";
import Table from 'react-bootstrap/Table'




class StudentTable extends Component {
    state = {
        elements: []
    }

    updateElements = (event) => {
        event.preventDefault()
        this.retrieveData()
    }

    retrieveData() {
        axios.get("http://localhost:8080/student").then( (result) => {
            this.setState({elements: result.data})
            this.props.updateSibling()
        } )
    }

    componentWillMount() {
        this.props.getRef(this)
        this.retrieveData()
    }

    deleteStudent = (studentId) => {
        axios.delete("http://localhost:8080/student?studentId="+ studentId).then( (response) => {
            this.retrieveData()
        })
    }

    render() {
        let tableBody = this.state.elements.map( (element, index) => {
            return <tr key={index}>
                <td>
                    {element.id}
                </td>
                <td>
                    {element.name}
                </td>
                <td>
                    {element.surname}
                </td>

                <td>
                    {element.level}
                </td>

                <td>
                    <Button variant="danger" onClick={(event) => {
                        event.preventDefault()
                        this.deleteStudent(element.id)
                    }}>
                        Delete
                    </Button>
                </td>
            </tr>
        } )

        return (
            <div>
                <Table striped bordered hover size="sm">
                <thead>
                        <tr>
                            <th>Student ID</th>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Level</th>
                            <th>Delete</th>
                        </tr>
                </thead>
                <tbody>
                    {tableBody}
                </tbody>
                </Table>
            </div>
        )
    }

}

export default StudentTable