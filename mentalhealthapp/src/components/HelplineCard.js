const HelplineCard = ({ name, number, region, timings }) => (
    <div className="helpline-card">
        <h4>{name}</h4>
        <p><strong>Number:</strong> {number}</p>
        <p><strong>Region:</strong> {region}</p>
        <p><strong>Timings:</strong> {timings}</p>
    </div>
);

export default HelplineCard;
