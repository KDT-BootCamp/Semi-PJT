import styled from "styled-components";
import TrainCodeItem from "./TrainCodeItem";

const Wrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: center;

    & > * {
        :not(:last-child) {
            margin-bottom: 16px;
        }
    }
`;

function TrainCodeList (props) {

    return (
        <Wrapper>
        {
            props.data.map((train) => 
                <TrainCodeItem key={train.code}
                    type = {train.type}
                    value = {train.value}
                    code = {train.code}
                />
            
            )
        }
        </Wrapper>
    );
}

export default TrainCodeList;