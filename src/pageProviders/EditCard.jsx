import PageAccessValidator from "components/PageAccessValidator";
import PageContainer from "components/PageContainer";
import React from "react";
import EditCardPage from 'pages/EditCard';

const EditCard = () => (
    <PageAccessValidator>
        <PageContainer>
            <EditCardPage />
        </PageContainer>
    </PageAccessValidator>
);

export default EditCard;