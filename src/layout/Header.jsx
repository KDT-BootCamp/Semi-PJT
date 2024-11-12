import { useEffect, useState } from 'react';
import api from '../project/component/api/axios'
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { logout } from  '../redux/authSlice';


function Header() {

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [loginBoolean,setLoginBoolean] = useState(false);

    const logoutHandler = async () => {
        try{
            
            await api.get("project/logout");
            console.log("debug >>> logout success !! ");
            dispatch(logout());
            console.log("debug >>> redux data reset ")
            navigate("/");        
                
        } catch(error) {

            console.log("debug >>> error : " + error );
        
        }

    }

    useEffect(() => {
        const loginCheck = async () => {
            try{
            
                await api.get('project/me');
                setLoginBoolean(true);

            } catch(err) {

                setLoginBoolean(false);
                
            }
        };
        
        loginCheck();
    });


    return (
        <nav className="navbar navbar-expand-lg bg-body-tertiary">
            <div className="container-fluid">
                <span className="navbar-brand">Train</span>
                <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div className="navbar-nav">
                        <a className="nav-link active" aria-current="page" href="\home">Home</a>
                        <a className="nav-link active" href="\trainInfo">Train-info</a>
                        <a className="nav-link active" href="\trainCode">Train-Code</a>
                    </div>
                    {loginBoolean && (<button className="btn btn-outline-success" type="button" onClick={logoutHandler}>Logout</button>)}
                </div>
            </div>
        </nav>
    );
}

export default Header;