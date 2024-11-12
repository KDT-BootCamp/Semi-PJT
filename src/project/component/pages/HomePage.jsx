import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import { useDispatch } from 'react-redux';
import { loggedIn } from '../../../redux/authSlice';
import { useEffect } from "react";
import api from '../api/axios';
const Wraaper = styled.div`
    display: grid;
    place-items: center; /* 수직, 수평 중앙 정렬 */
    height: 70vh; /* 화면 전체 높이 */
    `;


function HomePage() {

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const infoPage = () => {
        
        alert("Train Info Page");
        navigate("/trainInfo");
        
    }

    const codePage = () => {
        
        alert("Train Code Page");
        navigate("/trainCode");
        
    }

    useEffect(() => {
        const checkLogin = async () => {
          try {
            const response = await api.get('project/me', { withCredentials: true });
            
            const user = response.data;

            dispatch(loggedIn([user.id, user.name, user.password]));
          } catch (error) {
            console.log("Login Check failed >> " , error);
            navigate('/');
          }
        };
      
        checkLogin();
      }, []);

    return(
        <Wraaper>
            <span><h1>Train Serivce</h1></span>
            <span><h3>Select Service</h3></span>
            <div className="d-grid gap-2 col-6 mx-auto">
                <button className="btn btn-primary" type="button" onClick={infoPage}>Train-info</button>
                <button className="btn btn-primary" type="button" onClick={codePage}>Train-Code</button>
            </div>
        </Wraaper>    
        );
}

export default HomePage;