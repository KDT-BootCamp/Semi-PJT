import styled from "styled-components";
import React , { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";
import TrainCodeList from "../list/TrainCodeList";
import { loggedIn } from '../../../redux/authSlice';
import { useEffect } from "react";
import { useDispatch } from "react-redux";


const Wraaper = styled.div`
    display: grid;
    place-items: center;
`;

const Center = styled.div`
    display: grid;
    place-items: center;
`;

function TrainCodePage(props) {

    const navigate = useNavigate();
    const dispatch = useDispatch();

    const [station,setStation] = useState();
    const stationHandler = (e) => setStation(e.target.value);

    const [apiData,setApiData] = useState([]);

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

    const submitHandler = async (e) => {
        e.preventDefault(); 

        const data = {
            
            "station" : station

        }

        console.log("Submitting data:", data);

        try {
            const response = await api.post("project/trainCode", data);
            console.log("debug >>> response:", response.data);
            setApiData(response.data);

            if(response.status === 200){
                alert("코드 정보 조회 중 입니다");
            }
            
        } catch (err) {

            alert("조회에 실패하였습니다");
            console.log("Error during request:", err);
            setStation(err.response.data.station);    
        }
    };

    const returnClick = () => {
        alert("HomePage로 이동합니다");
        navigate("/home");
    };

    return (
        <Wraaper>
            <span><h1>Train Code Service</h1></span>
            <br />
            <span><h5>Fill Data</h5></span>
            <br />
            <form className="row g-3" onSubmit={submitHandler}>
                <div>
                    <label htmlFor="inputText4" className="form-label"><b>Station_Name</b></label>
                    <input type="text" className="form-control" id="inputText4" placeholder="ex) 서울" onChange={stationHandler} value={station} />
                </div>
                <Center>
                    <div>
                        <button type="submit" className="btn btn-primary">Submit</button>
                        &nbsp;&nbsp;&nbsp;
                        <button type="button" className="btn btn-primary" onClick={returnClick}>Cancel</button>
                    </div>
                    <br />
                </Center>
            </form>
            <TrainCodeList data={apiData}/>
        </Wraaper>
    );
}

export default TrainCodePage;