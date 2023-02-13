import PageAccessValidator from "components/PageAccessValidator";
import PageContainer from "components/PageContainer";
import React from "react";
import CardsListPage from "pages/CardsList";

const CardsList = () => (
    <PageAccessValidator>
        <PageContainer>
            <CardsListPage />
        </PageContainer>
    </PageAccessValidator>
);

export default CardsList;