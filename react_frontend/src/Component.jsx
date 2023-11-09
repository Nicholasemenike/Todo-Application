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
            <div className='card-body text-white'>
                <h4 className='card-title'>Message</h4>
                <p className='card-title'>
                    Message this just a text of me text the stuff theat is meant also suppose to be for testing
                    Message this just a text of me text the stuff theat is meant also suppose to be for testing
                    Message this just a text of me text the stuff theat is meant also suppose to be for testing
                    {/* Message this just a text of me text the stuff theat is meant also suppose to be for testing
                    Message this just a text of me text the stuff theat is meant also suppose to be for testing */}
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
    return(
        <div id='emptydiv' className='card'>
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

const MainBody= () => {
    return(
        <div className="mainbody">
            {/* <NewCard/> */}
            <FormForNewTask/>
        </div>
    );
};

const FormForNewTask =() => {
    return(
        <form className='form form-check bg-info'>
            <h3 className='form-title text-white'>New Task</h3>
            <input name='tastname' className='form-control' type='text' required placeholder='Task Name'/>
            <input name='taskcontent' className='form-control' type='text' required placeholder='Task Content'/>
            <input name='date' className='form-control mb-0' type='date' required placeholder='Task Content'/>
            <div id='impdiv' className='d-flex float-end'>
                <h5 text='important' className='text-white mt-3'>Important</h5>
                <input className='ms-2 mt-1' id='check' name='important'  type='checkbox' />
            </div>
            
            <button className=' bg-black text-white' type='submit'>Add</button>
        </form>
    );
}

export { MainBody, SideNavigation };
