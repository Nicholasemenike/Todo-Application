import React, { useState } from 'react'

const GlobalState = () => {
    const [openModel, setOpenModel] = useState(false)
  return (
    <GlobalProvider ></GlobalProvider>
  )
}

export default GlobalState