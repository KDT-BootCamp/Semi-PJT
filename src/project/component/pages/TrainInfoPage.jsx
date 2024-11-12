import styled from "styled-components";
import TrainInfoList from "../list/TrainInfoList";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";
import { loggedIn } from '../../../redux/authSlice';
import { useDispatch } from "react-redux";

const Wraaper = styled.div`
    display: grid;
    place-items: center;
`;

const Center = styled.div`
    display: grid;
    place-items: center;
`;

function TrainInfo(props) {
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const [dpDate, setDpDate] = useState('');
    const [dpStation, setDpStation] = useState('');
    const [arrDate, setArrDate] = useState('');
    const [arrStation, setArrStation] = useState('');

    const dpDateHandler = (e) => setDpDate(e.target.value);
    const dpStationHandler = (e) => setDpStation(e.target.value);
    const arrDateHandler = (e) => setArrDate(e.target.value);
    const arrStationHandler = (e) => setArrStation(e.target.value);

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
            "startDate" : dpDate,
            "lastDate" : arrDate,
            "dpStation" : dpStation,
            "arrStation" : arrStation
        };

        console.log("Submitting data:", data);

        try {

            const response = await api.post("project/trainApi", data);
            console.log("debug >>> response:", response.data);
            setApiData(response.data);

            if(response.status === 200){
                alert("열차 정보 조회 중 입니다");
            }

        } catch (err) {
            alert("조회에 실패하였습니다");
            console.log("Error during request:", err);
            setArrDate(err.response.data.startDate);
            setArrStation(err.response.data.dpStation);
            setDpDate(err.response.data.dpDate);
            setDpStation(err.response.data.dpStation);
        }
    };

    const returnClick = () => {
        alert("HomePage로 이동합니다");
        navigate("/home");
    };

    return (
        <Wraaper>
            <span><h1>Train info Service</h1></span>
            <br />
            <span><h5>Fill Data</h5></span>
            <br />
            <form className="row g-3" onSubmit={submitHandler}>
                <div className="col-md-6">
                    <label htmlFor="inputText4" className="form-label"><b>Dp_Date</b></label>
                    <input type="text" className="form-control" id="inputText4" placeholder="ex) 20241201" onChange={dpDateHandler} value={dpDate} />
                </div>
                <div className="col-md-6">
                    <label htmlFor="inputText4" className="form-label"><b>Dp_Station</b></label>
                    <input type="text" className="form-control" id="inputText4" placeholder="ex) 서울" onChange={dpStationHandler} value={dpStation} />
                </div>
                <div className="col-md-6">
                    <label htmlFor="inputText4" className="form-label"><b>Arr_Date</b></label>
                    <input type="text" className="form-control" id="inputText4" placeholder="ex) 20241201" onChange={arrDateHandler} value={arrDate} />
                </div>
                <div className="col-md-6">
                    <label htmlFor="inputText4" className="form-label"><b>Arr_Station</b></label>
                    <input type="text" className="form-control" id="inputText4" placeholder="ex) 부산" onChange={arrStationHandler} value={arrStation} />
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
            <TrainInfoList data={apiData} /> 
        </Wraaper>
    );
}

export default TrainInfo;