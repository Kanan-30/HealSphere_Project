const CrisisStepCard = ({ question, options, pdf_link }) => (
    <div className="crisis-step-card">
        <h4>{question}</h4>
        <ul>
            {options.map((option, idx) => <li key={idx}>{option}</li>)}
        </ul>
        <a href={pdf_link} target="_blank" rel="noopener noreferrer">Read More</a>
    </div>
);

export default CrisisStepCard;
