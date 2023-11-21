import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.css';
import React, { useEffect, useState } from "react";
import './Component.css';
import { useForm } from './GlobalState';
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
            <label className="userimage">userEmail</label>
            <img src={img} className="userimage" alt='user image'/>
        </div>
    );
}



const OccupiedCard = () => {
    const U = localStorage.getItem("userid")
    const [tasklist, setTasklist] = useState([]);
    useEffect(() =>{
        axios.get(`http://localhost:8080/user/task/all/${U}`)
        .then(response=> {
            console.log("in the collection zone")
            console.log(tasklist)
            setTasklist(response.data)
        }
            )
        .catch(error => console.log('error fetching task', error))
    },[])
    return(
        <div className='card'>
            {tasklist.map((task) => {
            <div className='card-body text-white' id='occupied'>
                <h4 className='card-title mt-2'>{task.name}</h4>
                <p id='carp' className='card-title' style={{Height:'90px', maxHeight:'90px', fontSize:'15px'}}>
                    Message thisis meant also suppose to be for testing
                </p>
                <div id='bt' className='d-flex justify-content-between '>
                    <div className='mt-2'>
                        <label>1/2/2024</label>
                        <p id='con' className='bg-success'>Completed</p>
                    </div >
                    <i className='fa fa-pen-to-square mt-4'><i className='fa fa-trash ms-3'></i></i>
                </div>
            </div>
            })}
        </div>
    );
}
const NewCard =() => {
    const {openForm} = useForm();
    
    return(
        <div id='emptydiv' className='card' onClick={
            openForm
        }>
            <div id='divv' className='card-body text-white d-flex' onClick={openForm}>
                <h4 className='card-title'>New Task</h4>
                <i className='fa fa-plus mt-1 ms-3'></i>
            </div>
        </div>
    );
}

const Signout = () => {
    const {openConfirm} = useForm();
    return(
            <div className='buttomdiv mb-5'>
                <i className='fa fa-right-to-bracket' onClick={openConfirm}/>
            </div>
    );
}

const Menuitems =() => {
    const {home,
        toHome,
        notHome,
        completed,
        toCompleted,
        notCompleted,
        incomplete,
        toIncomplete,
        notIncomplete,
        important,
        toImportant,
        notImportant,} = useForm();
    const [aciveNav, SetActiveNavigation] = useState('home');
    const handleNavOnClick = (navItem) => {
        SetActiveNavigation(navItem)
    }
    return(
        <div id='div1' className="centerdiv p-0">
            <nav>
                <ul>
                    <li 
                    className={`mb-2 ${aciveNav === 'home' ? 'active' : ''}`}
                    onClick={() => {
                        toHome();
                        notCompleted();
                        notImportant();
                        notIncomplete();
                        handleNavOnClick('home')
                    }}
                    >
                         <i className='fa fa-home me-2'></i>
                    </li>
                    
                    <li 
                    className={` mb-2 ${aciveNav === 'important' ? 'active' : ''}`}
                    onClick={() => {
                        notHome();
                        notCompleted();
                        toImportant();
                        notIncomplete();
                        handleNavOnClick('important')
                    }}
                    >
                        <i className='fa fa-check-to-slot me-2'></i>
                    </li>
                    
                    <li 
                    className={`text-white mb-2 ${aciveNav === 'complete' ? 'active' : ''}`}
                    onClick={() => {
                        notHome();
                        toCompleted();
                        notImportant();
                        notIncomplete();
                        handleNavOnClick('complete')
                    }}
                    >
                         <i className='fa fa-check me-2'></i>
                    </li>
                    
                    <li 
                    className={`text-white ${aciveNav === 'donow' ? 'active' : ''}`}
                    onClick={() => {
                        notHome();
                        notCompleted();
                        notImportant();
                        toIncomplete();
                        handleNavOnClick('donow')
                    }}
                    >
                         <i className='fa fa-list-check me-2'></i>
                    </li>
                </ul>
            </nav>
        </div>
    );
}

const Overlay =() => {
    return(
        <div id='overlay'>
        </div>
    );
}

const Login =() => {
    const {userIn} = useForm();
    const [accountExit, setAccountExit] = useState(false);
    const [signin, setSignin] = useState(false)
    const [signup, setSignup] = useState(true)
    const [valid, setValid] = useState(false)
    const [form, setForm] = useState({
        name: '',
        email: '',
        password: ''
    })
    const [loginForm, setLoginForm] = useState({
        email: '',
        password: ''
    })
    const handleChange = (e) => {
        e.preventDefault();
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };
    const register = async  () => {
        try {
            if(form.email.length< 3 || form.name.length < 3 || form.password.length< 3){
                setValid(true)
                setTimeout(()=>{
                    setValid(false)
                },2000)
                return;
            }
            const response = await axios.post('http://localhost:8080/user/register', form);
            if (response.status === 200) {
                localStorage.setItem("userid", response.data);
                userIn()
            }
          } catch (error) {
            setAccountExit(true)
            setTimeout(()=>{
                setAccountExit(false)
            },5000)
            console.error('Error during registration:', error);
          }
    };

    const login = async () => {
        const u = localStorage.getItem('userid')
        console.log(u)
        try{
            const response = axios.get('http://localhost:8080/user/task/all/'+u)
            console.log((await response).data)
        }catch(error){
            console.log(error)
        }

    }
    return(
        <form id='form-box'>
            <div>

                <h1 id='title'>{signup===true ? ('Sign Up') : ('Sign In')}</h1>
                <h5 id='valid' style={valid ? {display: 'block'} : {}}>Please enter a valid details for proper registration</h5>
                <h5 id='valid' style={accountExit ? {display: 'block'} : {}}>Account with {form.email} Already Exist</h5>

                <div className='input-field' style={signin===true ? {maxHeight:0} : {}} >
                    <i className='fa fa-user'></i>
                    <input id='name' name='name' className='' type='text' required={!signin} onChange={handleChange} placeholder='name'></input>
                </div> 
                
                <div className='input-field'>
                    <i className='fa fa-envelope'></i>
                    <input id='email' name='email' className='' type='email' required onChange={handleChange} placeholder='email'></input>
                </div>
                
                <div className='input-field'>
                    <i className='fa fa-lock' ></i>
                    <input name='password' className='' type='password' required onChange={handleChange} placeholder='password'></input>
                </div>
                <p className='text-black'>Forgot passcode ? <a href='#'>click here</a> </p>
            </div>

            <div className='btn-field'>
                <button type='submit' className={`${signup ? '' : 'disable'}`}  onClick={
                    (e) => {
                        if(signup){
                            e.preventDefault();
                            register();
                        }else{
                            setSignup(true)
                            setSignin(false)
                        }}
                     }>Sign Up</button>
                <button type='submit' className={`${signin===true ? '' : 'disable'}`} onClick={
                    (e) => {
                        e.preventDefault();
                        if(signin){
                            login();
                        }else{
                            setSignin(true)
                            setSignup(false)
                        }
                    }
                    }>Sign In</button>
            </div>

        </form>
    );
}

const Confirm = () => {
    const {openLogin, closeConfirm} = useForm();
    return(
        <form id='confirm'>
            <h3>Confirm Signout</h3>
            <div id='confirmicon' className='d-flex'>
                <i className='fa fa-check ms-5 bg-success' onClick={openLogin}></i>
                <i className='fa fa-x me-5 bg-danger' onClick={closeConfirm}></i>
            </div>
        </form>
    );
}

const MainBody= () => {
    const {home, incomplete, important, completed} = useForm();
    return(
        <div className="mainbody">
            <div>
                <h3 id='pin' className='text-white mb-4' style={{position:'absolute' ,margin:30}}>
                   {home===true ? ('Home') : ('')}
                   {incomplete===true ? ('Incomplete') : ('')}
                   {important===true ? ('Important') : ('')}
                   {completed===true ? ('Completed') : ('')}
                    </h3>
            </div>
            <div className="" id='taskcontain' style={{padding:'12px'}}>
                <OccupiedCard/>
                <NewCard/>
            </div>
                 
        </div>
    );
};

const FormForNewTask =(e) => {
    const currentTime = new Date();
    const u = localStorage.getItem('userid');
    const {closeForm} = useForm();
    const [formData, setFormData] = useState({
        name: '',
        description: 'c',
        time: currentTime.toISOString(),
        important: '',
        user: {u}
    })
    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
            
        });
    };
    const sendData =  (e)  =>  {
        e.preventDefault();
        axios.post(`http://localhost:8080/user/task/add`, {...formData, user : u})
        // fetch(`http://localhost:8080/user/task/add`, {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type' : 'application/json'
        //     },
        //     body: JSON.stringify(formData)
        // })
        // .then(response => response.json())
        .then(data => {
            console.log(data);
        })
        .catch(error => console.error('Error: ', error));
    }
    return(
        <form className='form form-check' id='formbox'>
            <div id='toptop'>
                <h3 className='form-title text-black'>New Task</h3>
                <i className='fa fa-x' onClick={() => {
                    closeForm();
                }}></i>
            </div>
            
            <label  htmlFor='name'>Title</label>
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

            <label htmlFor='description' >Description</label>
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

            <label htmlFor='date' >Date</label>
            <input 
            id='date' 
            name='date' 
            className='form-control mb-0' 
            type='date' 
            required 
            value={formData.date} 
            onChange={handleChange}
            />

            <div id='impdiv'>
                <div>
                    <label htmlFor='check' text='important' id='imp' className='text-black' >Important</label>
                    <input className='ms-2 mt-1' id='check' name='important'  type='checkbox' value={formData.important}/>
                </div>
                <div>
                    <button id='creat' onClick={sendData} className='' type='submit'><i className='fa fa-plus'></i> Create task</button>
                </div>
            
            </div>
        </form>
    );
}

const Allcontain =() => {
    const {showOverlay, showLogin, attemptToLogOut, signedIn, success, showForm, openSuccess} = useForm();
    return(
        <div className='maincontainer'>
        {showOverlay && <Overlay/>}
        {showLogin && <Login/>}
        {showForm && <FormForNewTask/>}
        {attemptToLogOut && <Confirm/>}
        {signedIn && <SideNavigation/>}
        {signedIn && <MainBody/>}
        {success && <SuccessCard/>}
        </div>
    );
}

const SuccessCard = () => {
    return(
        <div id='succes'>
            <div>
            <label>Success</label>
            <i className='fa fa-thumbs-up'></i>
            </div>
            <p>Check Email to Verify Account....</p>
        </div>
    );
}

export { Allcontain };
