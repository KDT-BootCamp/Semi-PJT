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
    `;

const TextContainer = styled.div`
    padding: 16px;
    border: 1px solid grey;
    border-radius: 8px;
    font-size: 20px;
    font-weight: 500;
`;
 
function TrainInfoItem(props) {
    return (
        <Container>
            <TextContainer>
                <p>운행 날짜: {props.run_ymd} 열차 번호: {props.trn_no}</p>
                <p>출발 역 코드: {props.dptre_stn_cd} 출발 역 이름: {props.dptre_stn_nm}</p>
                <p>도착 역 코드: {props.arvl_stn_cd} 도착 역 이름: {props.arvl_stn_nm}</p>             
                <p>열차 계획 출발 시간: {props.trn_plan_dptre_dt}</p>
                <p>열차 계획 도착 시간: {props.trn_plan_arvl_dt}</p>
            </TextContainer>
        </Container>
    );
}

export default TrainInfoItem;