import React, {Component} from "react";
import axios from "axios";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";


class StudentForm extends Component {


  

  submit = (e) => {
    e.preventDefault();
    let newName = document.getElementById("name").value;
    let newSurname = document.getElementById("surname").value;
    let newStudentLevel = document.getElementById("studentLevel").value;
    console.log(newStudentLevel)
    let objectToSend = {name: newName, surname: newSurname, level: newStudentLevel};

    axios.post("http://localhost:8080/student", objectToSend).then( (response) => {
      this.props.updateSibling();
    });

  }

  render() {
    return (
      <div >
        <Form onSubmit = {this.submit}>

          <Form.Group controlId="Name">
            <Form.Label >Name</Form.Label>
            <Form.Control type="string" placeholder="Name of the student" required={true} id = "name"/>
          </Form.Group>

          <Form.Group controlId="SURNAME">
            <Form.Label>Surname</Form.Label>
            <Form.Control type="string"  placeholder="Surname of the student"  required={true} id = "surname"/>
          </Form.Group>

          <Form.Group controlId="StudentLevel">
            <Form.Label>Student Level</Form.Label>
            <Form.Control as="select" id = "studentLevel">
              <option value = "UNDERGRADUATE">Undergraduate</option>
              <option value = "GRADUATE">Graduate</option>
            </Form.Control>
          </Form.Group>

          <Button variant="primary" type="Register"  >
            Register
          </Button>

        </Form>
      </div>
    );
  }
}

export default StudentForm;
