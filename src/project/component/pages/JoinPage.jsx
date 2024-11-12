import styled from "styled-components";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";

const Wraaper = styled.div`
    display: grid;
    place-items: center;
`;

const Center = styled.div`
    display: grid;
    place-items: center;
`;

function JoinPage() {
    
    const navigate = useNavigate();

    const [name, setName] = useState('');
    const [pwd, setPwd] = useState('');
    const [nameBoolean, setNameBoolean] = useState(false);
    const [pwdBoolean, setPwdBoolean] = useState(false);

    const nameHandler = (e) => {
        setName(e.target.value);
        setNameBoolean(false);
    }
    const pwdHandler = (e) => {
        setPwd(e.target.value);
        setPwdBoolean(false);
    }
    const nameCheck = async () => {
        
        const response =  await api.get(`project/namecheck?name=${name}`);
    
        console.log("debug >>> response status:", response.status);

        if(name === ''){
            
            alert("닉네임을 입력해주세요");

        } else {
            if(response.status === 200) {

                alert("생성 가능한 닉네임입니다");
                setNameBoolean(true);

            } else {
                
                alert("이미 존재하는 닉네임입니다");
            
            }
        }
    }

    const pwdCheck = async () => {

        const response =  await api.get(`project/pwdcheck?pwd=${pwd}`);
    
        console.log("debug >>> response status:", response.status);

        if(pwd === '') {

            alert("비밀번호를 입력해주세요");

        } else {
            if(response.status === 200) {

                alert("생성 가능한 비밀번호입니다");
                setPwdBoolean(true);

            } else {
                
                alert("이미 존재하는 비밀번호입니다");
                
            }
        }
    }

    const submitHandler = async (e) => {
        e.preventDefault(); 

        const data = {

            "name" : name,
            "password" : pwd

        };

        console.log("Submitting data:", data);

        try {

            const response = await api.post("project/join", data);
            console.log("debug >>> response:", response.data);
            
            alert("회원가입을 완료하였습니다");

        } catch (err) {
            alert("에러가 발생하였습니다")
            console.log("Error during request:", err);
        }
    };

    const returnClick = () => {
        alert("LoginPage로 이동합니다");
        navigate("/");
    };

    return(
        <Wraaper>
            <span><h1>Train Service Join</h1></span>
            <br />
            <span><h5>Fill Data</h5></span>
            <br />
            <form className="row g-3" onSubmit={submitHandler}>
                <div className="col-md-6">
                    <label htmlFor="inputText4" className="form-label"><b>Name</b></label>
                    <input type="text" className="form-control" id="inputText4" placeholder="ex) abcd123" onChange={nameHandler} value={name} />
                    <br/>
                <Center>
                    <button type="button" className="btn btn-secondary" onClick={nameCheck} disabled={nameBoolean}>Check</button>
                </Center>
                </div>
                <div className="col-md-6">
                    <label htmlFor="inputText4" className="form-label"><b>PassWord</b></label>
                    <input type="text" className="form-control" id="inputText4" placeholder="ex) pjt123!" onChange={pwdHandler} value={pwd} />
                    <br/>
                <Center>
                    <button type="button" className="btn btn-secondary" onClick={pwdCheck} disabled={pwdBoolean}>Check</button>
                </Center>
                    </div>
                <Center>
                    <br/>
                    <br/>
                    <div>
                        <button type="submit" className="btn btn-primary" disabled={!(nameBoolean&&pwdBoolean)}>Submit</button>
                        &nbsp;&nbsp;&nbsp;
                        <button type="button" className="btn btn-primary" onClick={returnClick}>Cancel</button>
                    </div>
                    <br />
                </Center>
            </form>
        </Wraaper>
    );
}

export default JoinPage;