import TrainInfoItem from "./TrainInfoItem"
import styled from "styled-components";

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


function TrainInfoList(props) {

    return (
        <Wrapper>
            {
            props.data.map( (train) => {
                return (
                    <TrainInfoItem 
                        key={train.trn_no}
                        arvl_stn_cd={train.arvl_stn_cd}
                        arvl_stn_nm={train.arvl_stn_nm}
                        dptre_stn_cd={train.dptre_stn_cd}
                        dptre_stn_nm={train.dptre_stn_nm}
                        run_ymd={train.run_ymd}
                        trn_no={train.trn_no}
                        trn_plan_arvl_dt={train.trn_plan_arvl_dt}
                        trn_plan_dptre_dt={train.trn_plan_dptre_dt} />
                );
            })}
        </Wrapper>
    );
}

export default TrainInfoList;