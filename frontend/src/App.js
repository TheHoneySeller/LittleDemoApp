import './App.css';
import React, { Component } from 'react';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import StudentForm from './components/StudentForm'
import StudentTable from './components/StudentTable';
import Container from 'react-bootstrap/Container'
import CourseForm from './components/CourseForm';
import RegistrationForm from './components/ReqistrationForm';
import CourseTable from './components/CourseTable';

class App extends Component {
  render() {
    return (
      <div className="App">
                  <Container>
                    
                      <Row>
                        <Col>
                          <StudentForm className="border" updateSibling={() => {
                            this.studentTable.retrieveData()
                          }}/>
                        </Col>
                        <Col>
                          <CourseForm updateSibling={ () => {
                            this.courseTable.retrieveData()
                          }}/>
                        </Col>
                        <Col>
                          <RegistrationForm updateSibling={ () => {
                            this.courseTable.retrieveData()
                          }}/>
                        </Col>
                      </Row>
                      
                      <div>
                        <StudentTable
                            getRef={ref => { this.studentTable = ref }}
                            updateSibling={ () => {
                              this.courseTable.retrieveData()
                            }}
                        />
                        <CourseTable
                          getRef={ref => {this.courseTable = ref}}
                        /> 
                      </div>

                  </Container>
              </div>
    );
  }
}

export default App;
