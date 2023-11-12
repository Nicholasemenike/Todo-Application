import 'bootstrap/dist/css/bootstrap.css';
import React, { useState } from "react";
import './Component.css';
import './fontawesome-free-6.4.2-web/css/all.css';
import img from './img/user.png';

const SideNavigation = () => {
    return(
        <div className="sidebar">
            <Topdetails/>
            <Menuitems/>
            <Signout/>
        </div>
    );
};

const Topdetails = () => {
    return(
        <div className='topdiv d-flex center mt-5 text-center'>
            <img src={img} className="userimage"/>
            <div className='ms-2'>
                <text className="username">Emenike</text>
                <text className="userlastname">Nicholas</text>
            </div>
        </div>
    );
}

const OccupiedCard = () => {
    return(
        <div className='card'>
            <div className='card-body text-white' id='occupied'>
                <h4 className='card-title'>Message</h4>
                <p className='card-title'>
                    Message this just a text of me text the stuff theat is meant also suppose to be for testing
                </p>
                <div id='bt' className='d-flex justify-content-between mb-5'>
                    <p id='con' className='bg-success '>Completed</p>
                    <i className='fa fa-pen-to-square'><i className='fa fa-trash ms-2'></i></i>
                </div>
            </div>
        </div>
    );
}
const NewCard =() => {
    const [showForm, setShowForm] = useState(false);
    const displayForm =() => {
        if(showForm){
            setShowForm(false)
        }else{
            setShowForm(true);
        }
    }
    return(
        <div id='emptydiv' className='card' onClick={displayForm}>
            {showForm && <FormForNewTask/>}
            <div id='divv' className='card-body text-white d-flex'>
                <h4 className='card-title'>New Task</h4>
                <i className='fa fa-plus mt-1 ms-3'></i>
            </div>

        </div>
    );
}

const Signout = () => {
    return(
            <div className='buttomdiv mb-5'>
                <text>sign out <i className='fa fa-right-to-bracket'/> </text>
            </div>
    );
}

const Menuitems =() => {
    const [aciveNav, SetActiveNavigation] = useState('all');
    const handleNavOnClick = (navItem) => {
        SetActiveNavigation(navItem)
    }
    return(
        <div id='div1' className="centerdiv me-4">
            <nav>
                <ul>
                    <li 
                    className={`text-white mb-2 ${aciveNav === 'home' ? 'active' : ''}`}
                    onClick={() => handleNavOnClick('home')}
                    >
                         <i className='fa fa-home me-2'></i>
                        All Tasks
                    </li>
                    
                    <li 
                    className={`text-white mb-2 ${aciveNav === 'important' ? 'active' : ''}`}
                    onClick={() => handleNavOnClick('important')}
                    >
                        <i className='fa fa-check-to-slot me-2'></i>
                        Important
                    </li>
                    
                    <li 
                    className={`text-white mb-2 ${aciveNav === 'complete' ? 'active' : ''}`}
                    onClick={() => handleNavOnClick('complete')}
                    >
                         <i className='fa fa-check me-2'></i>
                        Completed
                    </li>
                    
                    <li 
                    className={`text-white ${aciveNav === 'donow' ? 'active' : ''}`}
                    onClick={() => handleNavOnClick('donow')}
                    >
                         <i className='fa fa-list-check me-2'></i>
                        Do It Now
                    </li>
                </ul>
            </nav>
        </div>
    );
}

const Login =() => {
    const [form, setForm] = useState({
        name: '',
        email: '',
        number: ''
    })
    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
            
        });
    };
    const handleSubmit= () => {
        fetch('http://localhost:8080/user/register', {
            method: 'POST',
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(form)
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
        })
        .catch(error => console.error('Error: ', error));
    }
    return(
        <form id='loginForm'>
            <h1>Register</h1>
            <label className='' htmlFor='name'>Name</label>
            <input id='name' className='form-control' type='text' required onChange={handleChange}></input>

            <label className='' htmlFor='name'>Email</label>
            <input id='email' className='form-control' type='email ' required onChange={handleChange}></input>

            <label className='' htmlFor='name'>Number</label>
            <input id='number' className='form-control' type='number' required onChange={handleChange}></input>

           <button id='registerBtn' onSubmit={handleSubmit}>Register</button>
        </form>
    );
}

const MainBody= () => {
    return(
        <div className="mainbody">
            <Login/>
        </div>
    );
};

const FormForNewTask =() => {
    const [formData, setFormData] = useState({
        name: '',
        description: '',
        date: '',
        important: ''
    })
    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
            
        });
    };
    const sendData = () => {
        fetch('http://localhost:8080/user/addtask', {
            method: 'POST',
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
        })
        .catch(error => console.error('Error: ', error));
    }
    return(
        <form className='form form-check' >
            <div className='d-flex' id='toptop'>
                <h3 className='form-title text-white'>New Task</h3>
                <i className='fa fa-x' ></i>
            </div>
            
            <label className='text-white' htmlFor='name'>Title</label>
            <input 
            type='text'  
            id='name' 
            name='name' 
            className='form-control' 
            placeholder='Hello Nky '  
            value={formData.name} 
            onChange={handleChange}
            required 
            />

            <label htmlFor='description' className='text-white'>Description</label>
            <textarea 
            id='description' 
            name='description' 
            className='form-control mb-2' 
            type='text'  
            placeholder='Hello Nky Here...'   
            required 
            value={formData.description}
            onChange={handleChange}
            />

            <label htmlFor='date' className='text-white'>Date</label>
            <input 
            id='date' 
            name='date' 
            className='form-control mb-0' 
            type='date' 
            required 
            value={formData.date} 
            onChange={handleChange}
            />

            <div id='impdiv' className='d-flex float-end'>
                <label htmlFor='check' text='important' id='imp' className='text-white mt-3' >Important</label>
                <input className='ms-2 mt-1' id='check' name='important'  type='checkbox' value={formData.important}/>
            </div>
            
            <button onClick={sendData} className='' type='submit'><i className='fa fa-plus'></i> Create task</button>
        </form>
    );
}

export { MainBody, SideNavigation };
