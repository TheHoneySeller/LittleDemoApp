import React, {Component} from "react";
import axios from "axios";
import Button from "react-bootstrap/Button";
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Form from "react-bootstrap/Form";


class RegistrationForm extends Component {

  submitToEnroll = () => {
    let studentId = document.getElementById("studentsToEnroll").value
    let courseId = document.getElementById("coursesToEnroll").value

    axios.post("http://localhost:8080/enrollStudent?studentId=" + studentId +"&courseId=" + courseId).then( (response) => {
      this.props.updateSibling()
    });

  }

  submitToCancelEnrollment = () => {
    let studentId = document.getElementById("studentsToCancelEnrollment").value
    let courseId = document.getElementById("coursesToCancelEnrollment").value

    axios.delete("http://localhost:8080/cancelEnrollment?studentId=" + studentId +"&courseId=" + courseId).then( (response) => {
      this.props.updateSibling()
    });
  }


  render() {

    return (
      <div >
        <Form>
          <Row className="align-items-center">
            <Col>
              <Form.Group controlId="CoursesToEnroll">
                <Form.Label>Course Id</Form.Label>
                <Form.Control type="number" id = "coursesToEnroll">
                </Form.Control>
              </Form.Group>
            </Col>
            <Col>
              <Form.Group controlId="StudentsToEnroll">
                <Form.Label>Student Id</Form.Label>
                <Form.Control type="number" id = "studentsToEnroll">
                </Form.Control>
              </Form.Group>
            </Col>
            <Col>
              <Button variant="primary"  onClick= {this.submitToEnroll}>
                Enroll Student
              </Button>
            </Col>
          </Row>
          <Row className="align-items-center">
            <Col>
              <Form.Group controlId="CoursesToCancelEnrollment">
                <Form.Label>Course Id</Form.Label>
                <Form.Control type="number" id = "coursesToCancelEnrollment">
                </Form.Control>
              </Form.Group>
            </Col>
            <Col>
              <Form.Group controlId="StudentsToCancelEnrollment">
                <Form.Label>Student Id</Form.Label>
                <Form.Control type="number" id = "studentsToCancelEnrollment">
                </Form.Control>
              </Form.Group>
            </Col>
            <Col>
              <Button variant="danger"  onClick={ (event) => {
                this.submitToCancelEnrollment()
              }}> 
                Cancel Enrollment
              </Button>
            </Col>
          </Row>

        </Form>
      </div>
    );
  }
}

export default RegistrationForm;
