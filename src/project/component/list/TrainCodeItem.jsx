import styled from "styled-components";

const Container = styled.div`
    display: flex;
    justify-content: center; /* 가로로 중앙 정렬 */
    align-items: center;     /* 세로로 중앙 정렬 */
    padding: 10px 0;         
    cursor: pointer;
    background: white;
    :hover {
        background: lightgrey;
    }
    width : 100%;    

    `;

const TextContainer = styled.div`
    padding: 16px;
    border: 1px solid grey;
    border-radius: 8px;
    font-size: 20px;
    font-weight: 500;
    
`;

function TrainCodeItem(props) {


    return(
        <Container>
            <TextContainer>
                <p>역 타입 : {props.type}</p>
                <p>역 이름: {props.value}</p>
                <p>역 코드: {props.code}</p>             
            </TextContainer>
        </Container>
    );
}

export default TrainCodeItem;