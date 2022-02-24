import React, {Component} from "react";
import axios from "axios";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";


class MyForm extends Component {


  state = {
    name: "",
    surname: "",
    stype: ""
  }

  submit = (e) => {
    e.preventDefault();
    let newName = document.getElementById("name").value;
    let newSurname = document.getElementById("surname").value;
    let newStype = document.getElementById("stype").value;
    let objectToSend = {name: newName, surname: newSurname, stype: newStype};

    axios.put("http://localhost:8081/newstudent", objectToSend).then( (response) => {
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

          <Form.Group controlId="exampleForm.ControlSelect1">
            <Form.Label>Type</Form.Label>
            <Form.Control as="select" id = "stype">
              <option value = "Undergraduate">Undergraduate</option>
              <option value = "Graduate">Graduate</option>
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

export default MyForm;
