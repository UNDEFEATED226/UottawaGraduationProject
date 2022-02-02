import './App.css';
import { Component } from 'react';

class App extends Component {
  state = {
    events: []
  };

  async componentDidMount() {
    const response = await fetch('/main_events/find_all');
    const body = await response.json();
    this.setState({events: body});
  }

  render() {
    const {events} = this.state;
    return (
      <div className="App">
        <p>
          {events.map(e =>
            <div>
              {e.name_en}/{e.name_fr} ({new Date(e.start_date).getFullYear()} to {new Date(e.end_date).getFullYear()})
            </div>
          )}
        </p>
      </div>
    );
  }
}

export default App;
