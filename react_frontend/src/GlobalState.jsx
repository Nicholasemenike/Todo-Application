import React, { createContext, useContext, useState } from 'react';

export const FormContext = createContext();

export const FormProvider = ({children}) => {
  const [showForm, setShowForm] = useState(false);
  const [showOverlay, setOverlay] = useState(false);
  const [showLogin, setShowLogin] = useState(false);
  const [home, setHome] = useState(true);
  const [completed, setCompleted] = useState(false);
  const [important, setImportant] = useState(false);
  const [incomplete, setIncomplete] = useState(false);
  const [attemptToLogOut, setAttemptToLogOut] = useState(false);
  const [signedIn, setSignedIn] = useState(false);

  const userIn = () => {
    setSignedIn(true);
  }
  
  const userout = () => {
    setShowLogin(true);
    setSignedIn(false);
  }

  const openConfirm = () => {
    setOverlay(true);
    setAttemptToLogOut(true);
  }

  const closeConfirm = () => {
    setOverlay(false);
    setAttemptToLogOut(false);
  }

  const toHome = () => {
    setHome(true);
  }

  const notHome = () => {
    setHome(false);
  }
  
  const toCompleted = () => {
    setCompleted(true);
  }

  const notCompleted = () => {
    setCompleted(false);
  }
  
  const toImportant = () => {
    setImportant(true);
  }

  const notImportant = () => {
    setImportant(false);
  }
  
  const toIncomplete = () => {
    setIncomplete(true);
  }

  const notIncomplete = () => {
    setIncomplete(false);
  }

  const openLogin = () => {
    setAttemptToLogOut(false);
    setSignedIn(false);
    setOverlay(true);
    setShowLogin(true);
  }  
  
  const closeLogin = () => {
    setOverlay(false);
    setShowLogin(false);
  }

  const openForm = () => {
    setOverlay(true);
    setShowForm(true);
  }
  const closeForm =() => {
    setOverlay(false);
    setShowForm(false);
  }

  return(
    <FormContext.Provider value={{
      attemptToLogOut,
      openConfirm,
      closeConfirm,
      home,
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
      notImportant,
      showLogin,
      openLogin,
      closeLogin,
      showForm, 
      openForm, 
      closeForm,
      signedIn,
      showOverlay,
      }}>
      {children}
    </FormContext.Provider>
  );
}

export const useForm = () => {
  return useContext(FormContext);
}