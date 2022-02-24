import './App.css';
import React from 'react';
import MyForm from './components/form'
import Container from 'react-bootstrap/Container'

function App() {
  return (
    <div className="App">
                <Container>
                    <MyForm updateSibling={() => {
                        this.table.retrieveData()
                    }}/>
                </Container>
            </div>
  );
}

export default App;
