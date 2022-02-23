import './App.css'
import { Component } from 'react';
import SideBar from './components/SideBar'
import Header from './components/Header'
import Checkbox from './components/Checkbox';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Header username="Joe"/>
        <SideBar selected="MyProducts"/>
        <div className='mainSection'>
          <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Minima beatae optio fugiat neque odio blanditiis? Necessitatibus dolor nobis labore doloribus nesciunt excepturi ullam hic minus quam harum! Iure, dolor aperiam quidem illum officiis, nesciunt dolorum adipisci vero voluptates alias nisi veniam cum repellendus unde labore maiores nobis eos, commodi temporibus? Quia rem architecto nisi natus odit? Maxime quaerat beatae alias quas ratione architecto sint ducimus ab, consequuntur consequatur provident aspernatur sapiente rerum unde eligendi totam voluptates. Eum, eos quo. Incidunt totam corporis ipsum tenetur quibusdam laborum illum qui nesciunt adipisci ex blanditiis, praesentium, delectus quaerat odio debitis porro ducimus sapiente ut neque iure culpa consequatur? A neque architecto, itaque laudantium ratione fugit, veniam earum minus iure nemo debitis, expedita quisquam autem repudiandae amet necessitatibus sapiente explicabo molestiae eaque error recusandae animi? Odio suscipit corporis consequuntur, possimus sint assumenda, amet similique labore quae dignissimos nisi doloribus impedit a tenetur repudiandae nulla eos laboriosam. In eum quidem debitis amet deleniti beatae, quod ipsam hic labore, quam dolor consequatur, at aspernatur eius incidunt libero exercitationem illo atque? Aperiam, voluptatum aliquid! Vero exercitationem consequuntur architecto aperiam modi nesciunt porro atque. Id itaque expedita impedit ullam ab eaque necessitatibus, unde nam recusandae quisquam aperiam adipisci?
          </p>
          <Checkbox name='Test Checkbox'/>
          <p>
            Lorem ipsum dolor sit, amet consectetur adipisicing elit. Vel sunt praesentium velit assumenda ad obcaecati! Quidem impedit error ullam, blanditiis sit cupiditate. Architecto ipsam illum animi! Facere sequi dolor voluptatum aliquam dolores ipsam, explicabo recusandae assumenda? Sapiente atque itaque, nihil, reprehenderit rerum ex dolorum modi harum ad laborum corrupti sed quibusdam! Facilis temporibus eius odit ex alias, explicabo velit asperiores deleniti debitis mollitia maxime nesciunt corporis. Similique sequi voluptatibus error quo est sunt perferendis nostrum asperiores provident, fuga minus aut minima quis dolores illum id odio at qui unde voluptas atque sint delectus? A pariatur illo ab rem optio, nemo excepturi aspernatur suscipit expedita iure illum magni incidunt quibusdam aut tenetur saepe voluptatem ipsam praesentium quia, quidem totam eos! Magnam, aspernatur, animi temporibus nulla error rem amet odio deleniti dolor totam, quos cum quae at veniam! Assumenda blanditiis sit labore minima praesentium, magnam id ut aliquam perferendis accusantium est voluptatum delectus rerum repellat dolores amet incidunt aut ipsam culpa laudantium. Debitis consectetur itaque ex ab incidunt tempora nam non vero? Perferendis, nostrum aut similique voluptas facilis possimus autem sed, fugit modi cumque, repudiandae libero sequi. Harum, saepe! Quisquam ut magni recusandae iure at provident, velit odit iusto nihil delectus id?
          </p>
        </div>
      </div>
    );
  }
}

export default App;
