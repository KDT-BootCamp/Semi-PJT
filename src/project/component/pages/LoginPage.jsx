import styled from "styled-components"
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from '../api/axios';

const Wraaper = styled.div`
    display: grid;
    place-items: center; /* 수직, 수평 중앙 정렬 */
    height: 80vh; /* 화면 전체 높이 */
    `;



function LoginPage() {

    const navigate = useNavigate();

    const [ name, setName ] = useState('');
    const nameHandler = (e) => {
        setName(e.target.value);
    }

    const [ pwd, setPwd ] = useState('');
    const pwdHandler = (e) => {
        setPwd(e.target.value);
    }

    const loginEvent = async () => {
        try{
            const data = {
                password : pwd,
                name : name
            }
            const response = await api.post('project/login',data);
            
            console.log("LoginPage debug >>> axios post response ", response);

            if(response.status === 200){

                alert(name + "님 환영합니다");
                navigate("/home");

            }

        } catch(err){
            alert("잘못입력하셨습니다");
            console.log(err);
        }
    }

    const joinEvent = () => {
        
        alert("회원가입페이지로 이동합니다");
        navigate("/join");

    }

    return(
    
        <Wraaper>
            <span><h1>Train Serivce</h1></span>
            <div className="row g-2">
                <div className="col-md">
                    <div className="form-floating">
                        <input type="text" className="form-control" id="floatingInput1" placeholder="UserName" value={name} onChange={nameHandler}/>
                        <label htmlFor="floatingInput1">UserName</label>
                    </div>
                </div>
                <div className="col-md">
                    <div className="form-floating">
                        <input type="password" className="form-control" id="floatingInput2" placeholder="PassWord" value={pwd} onChange={pwdHandler}/>
                        <label htmlFor="floatingInput2">PassWord</label>
                    </div>
                </div>
                <button type="button" className="btn btn-primary" onClick={loginEvent}>Login</button>
                <button type="button" className="btn btn-primary" onClick={joinEvent}>Join</button>
            </div>
    </Wraaper>

    );
}

export default LoginPage;