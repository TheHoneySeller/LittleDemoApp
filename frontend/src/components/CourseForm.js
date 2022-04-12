import React, {Component} from "react";
import axios from "axios";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";


class CourseForm extends Component {



  submit = (e) => {
    e.preventDefault();
    let newTitle = document.getElementById("title").value;
    let newCourseLevel = document.getElementById("courseLevel").value;
    let objectToSend = {title: newTitle, level: newCourseLevel};

    axios.post("http://localhost:8082/course", objectToSend).then( (response) => {
      this.props.updateSibling();
    });

  }

  render() {
    return (
      <div >
        <Form onSubmit = {this.submit}>

          <Form.Group controlId="Title">
            <Form.Label >Title</Form.Label>
            <Form.Control type="string" placeholder="Title of the Course" required={true} id = "title"/>
          </Form.Group>

          <Form.Group controlId="CourseLevel">
            <Form.Label>Course Level</Form.Label>
            <Form.Control as="select" id = "courseLevel">
              <option value = "UNDERGRADUATE">Undergraduate</option>
              <option value = "GRADUATE">Graduate</option>
            </Form.Control>
          </Form.Group>

          <Button variant="primary" type="Register"  >
            Create Course
          </Button>

        </Form>
      </div>
    );
  }
}

export default CourseForm;
