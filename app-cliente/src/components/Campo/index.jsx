import React from "react";

const Campo = ({campo, label, placeholder, setValue, type}) => {
    return (
        <>
            <label htmlFor={campo} className="mb-0 form-label">{label}</label>
            <input type={type} id={campo} placeholder={placeholder} onChange={setValue} className="form-control" />
        </>
    )
}

export default Campo;