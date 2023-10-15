import AuthSection from 'components/Headers/AuthSection'
import React from 'react'

const UnauthorizedLayout = () => {
  return (
    <>
        <Header userSection={AuthSection}/>
    </>
  )
}

export default UnauthorizedLayout